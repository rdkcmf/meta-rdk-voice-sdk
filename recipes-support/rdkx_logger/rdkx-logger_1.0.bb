SUMMARY = "RDK logger provides a shared library that can be used to control and emit logs in a standardized format."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_rdkx-logger = "${AUTOREV}"
SRCREV_FORMAT  = "rdkx-logger"

SRC_URI = "${RDK_GENERIC_ROOT_GIT}/rdkx_logger/generic;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH};name=rdkx-logger"

S = "${WORKDIR}/git"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig ${INHERIT_COMCAST_BREAKPAD}

FILES_${PN}_append = " ${includedir}/rdkx_logger.h"
FILES_${PN}_append = " ${includedir}/rdkx_logger_modules.h"
FILES_${PN}_append = " ${sysconfdir}/rdkx_logger.json"

PROVIDES = "rdkx-logger"

DEPENDS = "gperf-native jansson rdkversion"

INCLUDE_DIRS = ""

CONFIG_FILE_DIR_NAME_DEV ?= '/opt'
CONFIG_FILE_DIR_NAME_PRD ?= '/etc'

CFLAGS_append = " -rdynamic -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE -DXLOG_MODULE_ID=XLOG_MODULE_ID_XLOG -DRDK_PLATFORM -Wall -Werror ${INCLUDE_DIRS}"
CFLAGS_append = ' -DXLOG_CONFIG_FILE_DIR_NAME_PRD=\"""${CONFIG_FILE_DIR_NAME_PRD}\""" -DXLOG_CONFIG_FILE_DIR_NAME_DEV=\"""${CONFIG_FILE_DIR_NAME_DEV}\"""'

LDFLAGS_append = " -lrdkversion -ljansson"

DEPENDS_append_voicecontrol = " curtail"
CFLAGS_append_voicecontrol  = " -DUSE_CURTAIL"
LDFLAGS_append_voicecontrol = " -lcurtail"

export STAGING_BINDIR_NATIVE
export RDKX_LOGGER_MODULE_TO_C="${S}/src/rdkx_logger_modules_to_c.py"

ANSI_CODES_DISABLED ?= "false"

do_disable_ansi_codes() {
   if [ "${ANSI_CODES_DISABLED}" = "true" ]; then
      sed -i -e "s/\(#define\s*XLOG_OPTS_DEFAULT.*\)\s*|\s*XLOG_OPTS_COLOR/\1/g" ${S}/src/rdkx_logger.h
   fi
}

addtask disable_ansi_codes after do_patch before do_configure

EXTRA_OECONF_append = " GIT_BRANCH=${RDK_GIT_BRANCH}"
