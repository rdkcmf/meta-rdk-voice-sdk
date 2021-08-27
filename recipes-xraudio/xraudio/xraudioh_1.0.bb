SUMMARY = "RDK version provides a shared library that can be used to access the RDK software version information."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_xraudioh = "${AUTOREV}"
SRCREV_FORMAT   = "xraudioh"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xraudio;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xraudioh"

S = "${WORKDIR}/git"

FILES_${PN} += "${includedir}/xraudio.h \
                ${includedir}/xraudio_hal.h \
                ${includedir}/xraudio_eos.h \
                ${includedir}/xraudio_dga.h \
                ${includedir}/xraudio_kwd.h \
                ${includedir}/xraudio_sdf.h \
                ${includedir}/xraudio_ovc.h \
                ${includedir}/xraudio_ppr.h \
                ${includedir}/xraudio_common.h \
                ${includedir}/xraudio_platform.h \
                ${includedir}/xraudio_version.h \
               "
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
   install -d ${D}${includedir}
   install -m 644 ${S}/src/xraudio.h                ${D}${includedir}
   install -m 644 ${S}/src/xraudio_hal.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_eos.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_dga.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_kwd.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_sdf.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_ovc.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_ppr.h            ${D}${includedir}
   install -m 644 ${S}/src/xraudio_common.h         ${D}${includedir}
   install -m 644 ${S}/src/xraudio_platform.h       ${D}${includedir}
   install -m 644 ${S}/src/xraudio_version.h        ${D}${includedir}
}

ALLOW_EMPTY_${PN} = "1"
