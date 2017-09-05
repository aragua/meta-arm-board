FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#PR := "${PR}.1"

KCONFIG_MODE = "--alldefconfig"

SRC_URI += "file://defconfig"