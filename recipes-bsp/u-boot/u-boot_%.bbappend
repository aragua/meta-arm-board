FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_riotboard = " file://0001-custom-env.patch"