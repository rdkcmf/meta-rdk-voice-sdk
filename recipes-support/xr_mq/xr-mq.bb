SUMMARY = "xr_mq is a message queue implementation, intended to replace the usage of POSIX message queues"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0+git${SRCPV}"

SRCREV_xr-mq = "${AUTOREV}"

SRC_URI = "${RDK_GENERIC_ROOT_GIT}/xr_mq;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH};name=xr-mq"

S = "${WORKDIR}/git"

FILES_${PN}_append = " ${includedir}/xr_mq.h"

XLOG_MODULE_NAME="XRMQ"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -Wall -Werror -rdynamic"

EXTRA_OECONF_append = " GIT_BRANCH=${RDK_GIT_BRANCH}"
