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
}
