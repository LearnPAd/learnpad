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
if [ $# -ne 1 ]
then
	customlog "ERROR" "\`prebuild' takes only 1 argument (the name of the component)" ${ERROR_PREBUILD_ARGS}
fi
declare -r COMPONENT_NAME="${1}"
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
		apt-get install ${DEP}
	done
else
	apt-get install ${DEPS_LIST[@]}
fi
}
