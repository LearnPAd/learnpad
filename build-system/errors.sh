#!/usr/bin/env bash
# Check if the file has already been loaded
if eval '[[ ${!__ERRORS_SH__[@]} ]]'
then
	return
else
	declare -r __ERRORS_SH__=""
fi

# Generic errors
declare -r SUCCESS=0
declare -r ERROR=1

# Errors for the main script
declare -r ERROR_ARGS=2
declare -r ERROR_COMPONENTS=3

# Errors for the `customlog' function
declare -r ERROR_CUSTOMLOG_ARGS=4
declare -r ERROR_CUSTOMLOG_LOG_LEVEL=5

# Errors for the `prebuild' script
declare -r ERROR_PREBUILD_ARGS=6

# Errors for the `build' script
declare -r ERROR_BUILD_ARGS=7

# Errors for the `postbuild' script
declare -r ERROR_POSTBUILD_ARGS=8

# Errors for the `launch' script
declare -r ERROR_LAUNCH_ARGS=9
