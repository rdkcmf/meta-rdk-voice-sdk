# Class to inherit when using rdkx logger.

DEPENDS_append  = " rdkx-logger"
RDEPENDS_${PN}_append  = " rdkx-logger"

CFLAGS_append   = " -DUSE_RDKX_LOGGER -DXLOG_MODULE_ID=XLOG_MODULE_ID_${XLOG_MODULE_NAME}"
CXXFLAGS_append = " -DUSE_RDKX_LOGGER -DXLOG_MODULE_ID=XLOG_MODULE_ID_${XLOG_MODULE_NAME}"
LDFLAGS_append  = " -lrdkx-logger"

EXTRA_OECONF_append = " --enable-rdkxlogger "

