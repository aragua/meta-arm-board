# Adapted from linux-imx.inc, copyright (C) 2013, 2014 O.S. Systems Software LTDA
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel ptest

require recipes-kernel/linux/linux-dtb.inc

SUMMARY = "Linux kernel"

SRC_URI = "\
    git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git \
    file://defconfig \
"
S = "${WORKDIR}/git"

PV = "4.11-git${SRCPV}"
SRCREV = "a351e9b9fc24e982ec2f0e76379a49826036da12"
DEPENDS += "lzop-native bc-native"
RDEPENDS_${PN}-ptest += "sed i2c-tools"
COMPATIBLE_MACHINE = "(mx6dl)"

