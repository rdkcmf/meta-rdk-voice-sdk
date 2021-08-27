SUMMARY = "xraudio utils provides a shared library that can be used to access xraudio utility functions."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_xraudio-utils = "${AUTOREV}"
SRCREV_FORMAT     = "xraudio-utils"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xraudio-utils;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xraudio-utils"

S = "${WORKDIR}/git"

DEPENDS = "xraudio"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

XLOG_MODULE_NAME="XRAUDIO"

INCLUDE_DIRS = ""

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -Wall -Werror -rdynamic  ${INCLUDE_DIRS}"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
