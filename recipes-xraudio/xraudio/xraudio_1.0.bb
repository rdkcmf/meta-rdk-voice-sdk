SUMMARY = "RDK version provides a shared library that can be used to access the RDK software version information."
DESCRIPTION = "TBD."
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"


PV = "1.0-git${SRCPV}"

SRCREV_xraudio = "${AUTOREV}"
SRCREV_FORMAT  = "xraudio"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/xraudio;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH};name=xraudio"

S = "${WORKDIR}/git"

XRAUDIO_KWD_COMPONENT ?= ""
XRAUDIO_EOS_COMPONENT ?= ""
XRAUDIO_DGA_COMPONENT ?= ""
XRAUDIO_SDF_COMPONENT ?= ""
XRAUDIO_OVC_COMPONENT ?= ""
XRAUDIO_PPR_COMPONENT ?= ""
XRAUDIO_DECODE_ADPCM  ?= "1"
XRAUDIO_DECODE_OPUS   ?= "1"
XRAUDIO_RESOURCE_MGMT ?= "1"

DEPENDS = "xraudioh xr-mq xr-timestamp xr-timer rdkversion libbsd ${XRAUDIO_KWD_COMPONENT} ${XRAUDIO_EOS_COMPONENT} ${XRAUDIO_DGA_COMPONENT} ${XRAUDIO_SDF_COMPONENT} ${XRAUDIO_OVC_COMPONENT} ${XRAUDIO_PPR_COMPONENT}"

DEPENDS_append = " ${@'xraudio-adpcm' if (d.getVar('XRAUDIO_DECODE_ADPCM', expand=False) == '1') else ''}"
DEPENDS_append = " ${@'xraudio-opus'  if (d.getVar('XRAUDIO_DECODE_OPUS',  expand=False) == '1') else ''}"

INHERIT_COMCAST_BREAKPAD := "${@bb.utils.contains('BBLAYERS', '${RDKROOT}/meta-rdk-comcast', 'comcast-breakpad', '',d)}"

inherit autotools pkgconfig coverity vsdk-utils rdkx-logger ${INHERIT_COMCAST_BREAKPAD}

XLOG_MODULE_NAME="XRAUDIO"

INCLUDE_DIRS = " \
    -I${PKG_CONFIG_SYSROOT_DIR}/usr/lib \
    "

CFLAGS_append = " -rdynamic -std=c11 -fPIC -D_REENTRANT -D_POSIX_C_SOURCE=200809L -D_GNU_SOURCE -Wall -Werror ${INCLUDE_DIRS}"

CFLAGS_append = " ${@'-DXRAUDIO_KWD_ENABLED'   if d.getVar('XRAUDIO_KWD_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_EOS_ENABLED'   if d.getVar('XRAUDIO_EOS_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_DGA_ENABLED'   if d.getVar('XRAUDIO_DGA_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_SDF_ENABLED'   if d.getVar('XRAUDIO_SDF_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_OVC_ENABLED'   if d.getVar('XRAUDIO_OVC_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_PPR_ENABLED'   if d.getVar('XRAUDIO_PPR_COMPONENT', True) else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_DECODE_ADPCM'  if (d.getVar('XRAUDIO_DECODE_ADPCM',  expand=False) == '1') else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_DECODE_OPUS'   if (d.getVar('XRAUDIO_DECODE_OPUS',   expand=False) == '1') else ''}"
CFLAGS_append = " ${@'-DXRAUDIO_RESOURCE_MGMT' if (d.getVar('XRAUDIO_RESOURCE_MGMT', expand=False) == '1') else ''}"

LDFLAGS_append = " -lc -lm -lpthread -lrdkversion -lbsd -lxr_mq -lxr-timer -lxr-timestamp"

LDFLAGS_append = " ${@'-lxraudio-kwd'   if d.getVar('XRAUDIO_KWD_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-eos'   if d.getVar('XRAUDIO_EOS_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-dga'   if d.getVar('XRAUDIO_DGA_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-sdf'   if d.getVar('XRAUDIO_SDF_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-ovc'   if d.getVar('XRAUDIO_OVC_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-ppr'   if d.getVar('XRAUDIO_PPR_COMPONENT', True) else ''}"
LDFLAGS_append = " ${@'-lxraudio-adpcm' if (d.getVar('XRAUDIO_DECODE_ADPCM',  expand=False) == '1') else ''}"
LDFLAGS_append = " ${@'-lxraudio-opus'  if (d.getVar('XRAUDIO_DECODE_OPUS',   expand=False) == '1') else ''}"
LDFLAGS_append = " ${@'-lrt'            if (d.getVar('XRAUDIO_RESOURCE_MGMT', expand=False) == '1') else ''}"

EXTRA_OECONF_append = "${@' --enable-resourcemgmt' if (d.getVar('XRAUDIO_RESOURCE_MGMT', expand=False) == '1') else ''}"

XRAUDIO_CONFIG_HAL     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_hal_config.json"
XRAUDIO_CONFIG_KWD     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_kwd_config.json"
XRAUDIO_CONFIG_EOS     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_eos_config.json"
XRAUDIO_CONFIG_DGA     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_dga_config.json"
XRAUDIO_CONFIG_SDF     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_sdf_config.json"
XRAUDIO_CONFIG_OVC     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_ovc_config.json"
XRAUDIO_CONFIG_PPR     = "${PKG_CONFIG_SYSROOT_DIR}/usr/include/xraudio_ppr_config.json"
XRAUDIO_CONFIG_OEM_ADD = "${S}/../xraudio_config_oem.add.json"
XRAUDIO_CONFIG_OEM_SUB = "${S}/../xraudio_config_oem.sub.json"

EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_HAL=${XRAUDIO_CONFIG_HAL}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_KWD=${XRAUDIO_CONFIG_KWD}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_EOS=${XRAUDIO_CONFIG_EOS}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_DGA=${XRAUDIO_CONFIG_DGA}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_SDF=${XRAUDIO_CONFIG_SDF}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_OVC=${XRAUDIO_CONFIG_OVC}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_PPR=${XRAUDIO_CONFIG_PPR}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_SUB=${XRAUDIO_CONFIG_OEM_SUB}"
EXTRA_OECONF_append = " XRAUDIO_CONFIG_JSON_ADD=${XRAUDIO_CONFIG_OEM_ADD}"

EXTRA_OECONF_append = " GIT_BRANCH=${CMF_GIT_BRANCH}"

addtask clean_oem_config before do_configure

do_clean_oem_config() { 
    rm -f ${XRAUDIO_CONFIG_OEM_ADD} ${XRAUDIO_CONFIG_OEM_SUB}
}
