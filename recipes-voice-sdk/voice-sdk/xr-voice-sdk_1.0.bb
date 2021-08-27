SUMMARY = "xr-voice-sdk provides a shared library that controls how and where speech gets distributed."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_xr-voice-sdk = "${AUTOREV}"
SRCREV_FORMAT       = "xr-voice-sdk"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr-voice-sdk;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-voice-sdk"

S = "${WORKDIR}/git"

DEPENDS = "xr-speech-router xr-speech-vrex"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

XLOG_MODULE_NAME="VSDK"

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -D_GNU_SOURCE -Wall -Werror -rdynamic"

LDFLAGS_append = " -lxrsr -lxrsv"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
