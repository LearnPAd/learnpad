#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__POSTBUILD_SH__[@]} ]]'
then
	return
else
	declare -r __POSTBUILD_SH__
fi

source ${__BUILD_SYSTEM_PATH__}/utils.sh

function postbuild() {
if [ $# -lt 1 ]
then
	customlog "ERROR" "\`postbuild' takes at least 1 argument (the name of the component)" ${ERROR_POSTBUILD_ARGS}
fi
declare INSTALL_DEPS=false
for ARG in $*
do
	if [[ ${ARG} == --* ]]
	then
		case ${ARG} in
			"-d" | "--install-deps" )
				declare INSTALL_DEPS=true
				;;
			"-nd" | "--noinstall-deps" )
				declare INSTALL_DEPS=false
				;;
			*)
				customlog "INFO" "\`${ARG}' is not parsed by \`postbuild' script."
				;;
		esac
	else
		declare -r COMPONENT_NAME="${ARG}"
	fi
done
if isnotcomponent ${COMPONENT_NAME}
then
	customlog "ERROR" "\`postbuild' takes a component's name as an argument" ${ERROR_POSTBUILD_ARGS}
fi

customlog "INFO" "[${COMPONENT_NAME}] Start postbuild."
declare -r COMPONENT_PATH="${__ROOT_PATH__}/${COMPONENT_NAME}"
declare -r OUT_PATH="${COMPONENT_PATH}/out"
declare -r RUNDEPS_FILE="${OUT_PATH}/rundeps.txt"
if [ -f "${RUNDEPS_FILE}" ]
then
	customlog "INFO" "[${COMPONENT_NAME}] Installing build dependencies."
	postbuild_installdependencies "$( cat ${RUNDEPS_FILE} )"
fi
declare -r START_FILE="${OUT_PATH}/start"
if [ -f "$START_FILE" ]
then
	customlog "INFO" "[${COMPONENT_NAME}] Start the component"
	bash "${START_FILE}"
	declare -r STOP_FILE="${OUT_PATH}/stop"
	customlog "INFO" "[${COMPONENT_NAME}] Stop the component"
	bash "${STOP_FILE}"
fi
}

function postbuild_installdependencies() {
declare -a DEPS_LIST=("$*")
if [ ${__LOG_LEVEL__} = "DEBUG" ]
then
	for DEP in ${DEPS_LIST}
	do
		customlog "DEBUG" "Installing ${DEP}"
		if $INSTALL_DEPS
		then
			sudo apt-get install ${DEP}
		else
			customlog "INFO" "Dependency \`${DEP}' will not be installed."
		fi
	done
else
	if $INSTALL_DEPS
	then
		sudo apt-get install ${DEPS_LIST[@]}
	else
		customlog "INFO" "Dependencies \`${DEPS_LIST[@]}' will not be installed."
	fi
fi
}
