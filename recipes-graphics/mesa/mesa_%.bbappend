# Enable Etnaviv support
PACKAGECONFIG_append_riotboard = " gallium"
GALLIUMDRIVERS_append_riotboard = ",etnaviv,imx"

PACKAGECONFIG_append_nitrogen6x = " gallium"
GALLIUMDRIVERS_append_nitrogen6x = ",etnaviv,imx"

