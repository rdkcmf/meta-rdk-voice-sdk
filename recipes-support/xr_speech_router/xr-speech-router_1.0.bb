SUMMARY = "xr-speech-router provides a shared library that controls how and where speech gets routed."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0-git${SRCPV}"

SRCREV_xr-speech-router = "${AUTOREV}"
SRCREV_FORMAT           = "xr-speech-router"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xr-speech-router;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xr-speech-router"

S = "${WORKDIR}/git"

DEPENDS = "libbsd xraudio xr-mq xr-timer util-linux xr-sm-engine"

INHERIT_BREAKPAD_WRAPPER := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk', 'breakpad-wrapper', '',d)}"

inherit autotools pkgconfig coverity vsdk-utils rdkx-logger ${INHERIT_BREAKPAD_WRAPPER}

XLOG_MODULE_NAME="XRSR"

INCLUDE_DIRS = "-I${PKG_CONFIG_SYSROOT_DIR}/usr/include/nopoll"

XRAUDIO_RESOURCE_MGMT ?= "1"

CFLAGS_append = " -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -D_GNU_SOURCE -Wall -Werror ${INCLUDE_DIRS} -rdynamic"

CFLAGS_append = " ${@'-DXRAUDIO_RESOURCE_MGMT' if (d.getVar('XRAUDIO_RESOURCE_MGMT', expand=False) == '1') else ''}"

LDFLAGS=" -lc -lbsd  -lpthread -lxraudio -lxr_mq -lxr-timer -lxrpSMEngine -lanl"

# Set to "1" in recipe append to enable/disable HTTP or WS support
ENABLE_HTTP_SUPPORT ?= "1"
ENABLE_WS_SUPPORT   ?= "1"
ENABLE_SDT_SUPPORT   ?= "0"


DEPENDS_append      = "${@ ' curl'               if (d.getVar('ENABLE_HTTP_SUPPORT', expand=False) == "1") else ''}"
EXTRA_OECONF_append = "${@ ' --enable-xrsr_http' if (d.getVar('ENABLE_HTTP_SUPPORT', expand=False) == "1") else ''}"

DEPENDS_append      = "${@ ' nopoll'             if (d.getVar('ENABLE_WS_SUPPORT',   expand=False) == "1") else ''}"
EXTRA_OECONF_append = "${@ ' --enable-xrsr_ws'   if (d.getVar('ENABLE_WS_SUPPORT',   expand=False) == "1") else ''}"


EXTRA_OECONF_append = "${@ ' --enable-xrsr_sdt' if (d.getVar('ENABLE_SDT_SUPPORT', expand=False) == "1") else ''}"


XRSR_CONFIG_XRAUDIO = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_config.json"
XRSR_CONFIG_OEM_ADD = "${S}/../xrsr_config_oem.add.json"
XRSR_CONFIG_OEM_SUB = "${S}/../xrsr_config_oem.sub.json"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH} XRSR_CONFIG_JSON_XRAUDIO=${XRSR_CONFIG_XRAUDIO} XRSR_CONFIG_JSON_SUB=${XRSR_CONFIG_OEM_SUB} XRSR_CONFIG_JSON_ADD=${XRSR_CONFIG_OEM_ADD}"

addtask clean_oem_config before do_configure

do_clean_oem_config() { 
    rm -f ${XRSR_CONFIG_OEM_ADD} ${XRSR_CONFIG_OEM_SUB}
}
