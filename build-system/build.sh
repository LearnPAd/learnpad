#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__BUILD_SH__[@]} ]]'
then
	return
else
	declare -r __BUILD_SH__
fi

source ${__BUILD_SYSTEM_PATH__}/utils.sh

function build() {
if [ $# -ne 1 ]
then
	customlog "ERROR" "\`build' takes only 1 argument (the name of the component)" ${ERROR_BUILD_ARGS}
fi
declare -r COMPONENT_NAME="${1}"
if isnotcomponent ${COMPONENT_NAME}
then
	customlog "ERROR" "\`build' takes a component's name as an argument" ${ERROR_BUILD_ARGS}
fi

customlog "INFO" "[${COMPONENT_NAME}] Start build."
declare -r COMPONENT_PATH="${__ROOT_PATH__}/${COMPONENT_NAME}"
declare -r BUILD_FILE="${COMPONENT_PATH}/build"
if [ -f "${BUILD_FILE}" ]
then
	cd "${COMPONENT_PATH}"
	bash ${BUILD_FILE}
fi
}
