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
if [ $# -ne 1 ]
then
	customlog "ERROR" "\`postbuild' takes only 1 argument (the name of the component)" ${ERROR_POSTBUILD_ARGS}
fi
declare -r COMPONENT_NAME="${1}"
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
customlog "INFO" "[${COMPONENT_NAME}] Start the component"
bash "${START_FILE}"
declare -r STOP_FILE="${OUT_PATH}/stop"
customlog "INFO" "[${COMPONENT_NAME}] Stop the component"
bash "${STOP_FILE}"
}

function postbuild_installdependencies() {
declare -a DEPS_LIST=("$*")
if [ ${__LOG_LEVEL__} = "DEBUG" ]
then
	for DEP in ${DEPS_LIST}
	do
		customlog "DEBUG" "Installing ${DEP}"
		sudo apt-get install ${DEP}
	done
else
	sudo apt-get install ${DEPS_LIST[@]}
fi
}
