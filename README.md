meta-arm-board
==============

Contains useful recipes for OE and BSPs.

Available machine:

 * riotboard.

Riotboard
---------

You can build image for riotboard:

$ TEMPLATECONF=userconf/riotboard source oe-init-build-env

$ bitbake core-image-weston

$ sudo dd if=tmp-glibc/deploy/images/riotboard/core-image-weston-riotboard.wic of=/dev/mmcblk0