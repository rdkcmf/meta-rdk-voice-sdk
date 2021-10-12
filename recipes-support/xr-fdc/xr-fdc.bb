SUMMARY = "xr-fdc is a library to help debug fd issues."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0+git${SRCPV}"

SRCREV_xr-fdc = "${AUTOREV}"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr-fdc;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-fdc"

S = "${WORKDIR}/git"

FILES_${PN}_append = " ${includedir}/xr_fdc.h"

XLOG_MODULE_NAME="XRFDC"

DEPENDS = ""

INHERIT_BREAKPAD_WRAPPER := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk', 'breakpad-wrapper', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_BREAKPAD_WRAPPER}

INCLUDE_DIRS = ""

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -Wall -Werror -rdynamic ${INCLUDE_DIRS}"

LDFLAGS_append = " -lbsd"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
