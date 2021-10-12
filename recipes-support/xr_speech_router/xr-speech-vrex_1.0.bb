SUMMARY = "xr-speech-vrex provides a shared library that handles vrex connection data."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_xr-speech-vrex = "${AUTOREV}"
SRCREV_FORMAT         = "xr-speech-vrex"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr-speech-vrex;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-speech-vrex"

S = "${WORKDIR}/git"

DEPENDS = "libbsd xr-speech-router jansson gperf-native"

INHERIT_BREAKPAD_WRAPPER := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk', 'breakpad-wrapper', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_BREAKPAD_WRAPPER}

XLOG_MODULE_NAME="XRSV"

INCLUDE_DIRS = ""

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -D_GNU_SOURCE -Wall -Werror ${INCLUDE_DIRS} -rdynamic"

XRSV_WS_NEXTGEN_DEVICE_TYPE?="stb"
CFLAGS_append = ' -DXRSV_WS_NEXTGEN_JSON_KEY_ELEMENT_ID_TYPE_VALUE=\"""${XRSV_WS_NEXTGEN_DEVICE_TYPE}\"""'

LDFLAGS=" -lc -lbsd -ljansson"

export STAGING_BINDIR_NATIVE

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
