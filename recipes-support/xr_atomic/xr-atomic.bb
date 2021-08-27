SUMMARY = "xr_atomic is a atomic int implementation, which takes advantage of stdatomic if available with a pthread fallback"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0+git${SRCPV}"

SRCREV_xr-atomic = "${AUTOREV}"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr_atomic;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-atomic"

S = "${WORKDIR}/git"

FILES_${PN}_append = " ${includedir}/xr_atomic.h"

XLOG_MODULE_NAME="XRATOMIC"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig coverity rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

CXXFLAGS_append = " -std=c++11 -fPIC -D_REENTRANT -rdynamic -Wall -Werror "

CFLAGS_append = " -std=c99 -Wall -Werror "

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"
