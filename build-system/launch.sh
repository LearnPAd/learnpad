#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__BUILD_SH__[@]} ]]'
then
	return
else
	declare -r __BUILD_SH__
fi

source ${__BUILD_SYSTEM_PATH__}/utils.sh

function launch() {
if [ $# -lt 2 ]
then
	customlog "ERROR" "\`launch' takes at least 2 argument (the name of the component and a value 'start' or 'stop' or 'restart')" ${ERROR_BUILD_ARGS}
fi
declare -r __COMPONENT_NAME__="$1"
declare -r __ACTION__="$2"
if isnotcomponent ${__COMPONENT_NAME__}
then
	customlog "ERROR" "\`start' takes a component's name as an argument" ${ERROR_BUILD_ARGS}
fi

declare -r COMPONENT_PATH="${__ROOT_PATH__}/${__COMPONENT_NAME__}"
declare -r START_FILE="${COMPONENT_PATH}/out/start"
declare -r STOP_FILE="${COMPONENT_PATH}/out/stop"
if [ "${__ACTION__}" = "stop" -o "${__ACTION__}" = "restart" ]
then
	if [ -f "${STOP_FILE}" ]
	then
		customlog "INFO" "Stopping [${__COMPONENT_NAME__}] component."
		bash ${STOP_FILE}
	fi
fi
if [ "${__ACTION__}" = "start" -o "${__ACTION__}" = "restart" ]
then
	if [ -f "${START_FILE}" ]
	then
		customlog "INFO" "Starting [${__COMPONENT_NAME__}] component."
		bash ${START_FILE}
	fi
fi
}
