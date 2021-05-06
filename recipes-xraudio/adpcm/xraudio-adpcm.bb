SUMMARY = "xraudio adpcm provides a shared library that can be used to decode Comcast's flavor of ADPCM to PCM."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "1.0+git${SRCPV}"

SRCREV_xraudio-adpcm = "${AUTOREV}"

SRC_URI = "${RDK_GENERIC_ROOT_GIT}/xraudio/adpcm;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH};name=xraudio-adpcm"

S = "${WORKDIR}/git"

inherit update-alternatives

FILES_${PN}_append = " ${includedir}/adpcm.h"

XLOG_MODULE_NAME="XRAUDIO"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

CFLAGS_append = " -std=c99 -Wall -Werror "

ALTERNATIVE_LINK_NAME[test] = "${bindir}/test"
ALTERNATIVE_PRIORITY = "90"
ALTERNATIVE_${PN} = "test"

EXTRA_OECONF_append = " GIT_BRANCH=${RDK_GIT_BRANCH}"