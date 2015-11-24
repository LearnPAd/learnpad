#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__UTILS_SH__[@]} ]]'
then
	return
else
	declare -r __UTILS_SH__=""
fi

source ${__BUILD_SYSTEM_PATH__}/errors.sh

function customlog() {
declare -r _ERROR_LEVEL_=1
declare -r _INFO_LEVEL_=2
declare -r _DEBUG_LEVEL_=3
declare -r LOG_SCRIPT="${0}"
if [ $# -ge 3 ]
then
	declare -r LOG_ERROR_CODE=${3}
fi
if [ $# -ge 2 ]
then
	declare -r LOG_MESSAGE="${2}"
	declare -r LOG_LEVEL="$( echo ${1} | tr '[:lower:]' '[:upper:]' )"
	if [ "${LOG_LEVEL}" = "ERROR" -o "${LOG_LEVEL}" = "INFO" -o "${LOG_LEVEL}" = "DEBUG" ]
	then
		if [ "${LOG_LEVEL}" = "ERROR" -a $# -ne 3 ]
		then
			declare -r LOG_LEVEL="ERROR"
			declare -r LOG_MESSAGE="\`customlog' takes 3 arguments for errors (the error code)."
			declare -r LOG_ERROR_CODE=${ERROR_CUSTOMLOG_ARGS}
		fi
	else
		declare -r LOG_LEVEL="ERROR"
		declare -r LOG_MESSAGE="\`customlog' has 3 possible log levels: 'ERROR', 'INFO' or 'DEBUG'."
		declare -r LOG_ERROR_CODE=${ERROR_CUSTOMLOG_LOG_LEVEL}
	fi
else
	declare -r LOG_LEVEL="ERROR"
	declare -r LOG_MESSAGE="\`customlog' takes 2 arguments (3 for errors)."
	declare -r LOG_ERROR_CODE=${ERROR_CUSTOMLOG_ARGS}
fi
declare -i LOG_LEVELN=$( eval "echo \${_${LOG_LEVEL}_LEVEL_}" )
declare -i WANTED_LOG_LEVELN=$( eval "echo \${_${__LOG_LEVEL__}_LEVEL_}" )
if [ "${LOG_LEVELN}" -le "${WANTED_LOG_LEVELN}" ]
then
	echo "${LOG_SCRIPT}: ${LOG_LEVEL}: ${LOG_MESSAGE}"
fi
if [ "${LOG_LEVEL}" = "ERROR" ]
then
	exit ${LOG_ERROR_CODE}
fi
}

function iscomponent() {
if [ $# -eq 1 ]
then
	if [ -d "${__ROOT_PATH__}/${1}" ]
	then
		return ${SUCCESS}
	fi
fi
return ${ERROR}
}

function isnotcomponent() {
if iscomponent ${1}
then
	return ${ERROR}
else
	return ${SUCCESS}
fi
}
