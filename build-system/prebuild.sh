#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__PREBUILD_SH__[@]} ]]'
then
	return
else
	declare -r __PREBUILD_SH__
fi

source ${__BUILD_SYSTEM_PATH__}/utils.sh

function prebuild() {
if [ $# -lt 1 ]
then
	customlog "ERROR" "\`prebuild' takes at least 1 argument (the name of the component)" ${ERROR_PREBUILD_ARGS}
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
				customlog "INFO" "\`${ARG}' is not parsed by \`prebuild' script."
				;;
		esac
	else
		declare -r COMPONENT_NAME="${ARG}"
	fi
done
if isnotcomponent ${COMPONENT_NAME}
then
	customlog "ERROR" "\`prebuild' takes a component's name as an argument" ${ERROR_PREBUILD_ARGS}
fi

customlog "INFO" "[${COMPONENT_NAME}] Start prebuild."
declare -r BUILDDEPS_FILE="${__ROOT_PATH__}/${COMPONENT_NAME}/builddeps.txt"
if [ -f "${BUILDDEPS_FILE}" ]
then
	customlog "INFO" "[${COMPONENT_NAME}] Installing build dependencies."
	prebuild_installdependencies "$( cat ${BUILDDEPS_FILE} )"
fi
}

function prebuild_installdependencies() {
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
