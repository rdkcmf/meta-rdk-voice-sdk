SUMMARY = "xr-timestamp is a set of functions to get/add/subtract/compare timestamps."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0+git${SRCPV}"

DEPENDS = "rdkx-logger"

SRCREV_xr-timestamp = "${AUTOREV}"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr-timestamp;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-timestamp"

S = "${WORKDIR}/git"

FILES_${PN}_append = " ${includedir}/xr_timestamp.h"

XLOG_MODULE_NAME="XRSTAMP"

INHERIT_BREAKPAD_WRAPPER := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk', 'breakpad-wrapper', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_BREAKPAD_WRAPPER}

INCLUDE_DIRS = ""

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -Wall -Werror -rdynamic ${INCLUDE_DIRS}"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
