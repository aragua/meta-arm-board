LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel ptest

SUMMARY = "Linux kernel"

SRC_URI = "\
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.13.y \
    file://defconfig \
"
S = "${WORKDIR}/git"

PV = "4.13-git${SRCPV}"
SRCREV = "94cd0e9dcdc13d2712d3a9ed749eac301dad160d"
#SRCREV = "a351e9b9fc24e982ec2f0e76379a49826036da12"
DEPENDS += "lzop-native bc-native"
RDEPENDS_${PN}-ptest += "sed i2c-tools"
COMPATIBLE_MACHINE = "(imx)"

