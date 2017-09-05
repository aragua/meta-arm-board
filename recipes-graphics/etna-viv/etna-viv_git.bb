# Copyright (C) 2015 Timesys Corporation

DESCRIPTION = "Open Source GPU driver for Vivante Cores"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba2fcc0480b365fcee43b28a0fa420e9"

DEPENDS = "libpng zlib"

SRC_URI = "git://github.com/etnaviv/etna_viv.git;branch=master"

SRCREV = "c9b3e67107514c4c44eefd608cde879feae6c68f"

S = "${WORKDIR}/git"
PR = "r1"
PV = "git${SRCPV}"

PACKAGES = "${PN} ${PN}-dev ${PN}-staticdev ${PN}-dbg"

GCABI="imx6_v4_6_9"

EXTRA_OEMAKE = "GCCPREFIX="${TARGET_PREFIX}"	\
		PLATFORM_CFLAGS="-isystem ${STAGING_INCDIR} ${TARGET_CC_ARCH} ${CFLAGS}"	\
		PLATFORM_CXXFLAGS="-isystem ${STAGING_INCDIR} ${TARGET_CC_ARCH} ${CXXFLAGS}"	\
		PLATFORM_LDFLAGS="--sysroot ${STAGING_DIR_HOST} -L ${STAGING_LIBDIR} -ldl ${LDFLAGS}" \
		PLATFORM_GL_LIBS="-lEGL -lGLESv2 -L${TOP}/lib/egl -Xlinker --allow-shlib-undefined" \
		GCABI=${GCABI}"

ETNAVIV_EXECUTABLES = " \
	fb/etna_gears \
	fb/particle_system \
	fb/mip_cube \
	fb/ps_sandbox \
	fb/stencil_test \
	fb/rotate_cube \
	fb/cubemap_sphere \
	fb/cube_companion \
	fb/displacement \
	fb/downsample_test \
	fb/alpha_blend \
	test2d/filterblt2d_fromplanar \
	test2d/stretchblt2d \
	test2d/bitblt2d_alpha \
	test2d/line2d \
	test2d/bitblt2d_rotate \
	test2d/bitblt2d_from_stream \
	test2d/bitblt2d \
	test2d/bitblt2d_palette \
	test2d/clear2d \
	test2d/line2d_patterned \
	test2d/filterblt2d \
	fb_rawshader/etna_gears \
	fb_rawshader/particle_system \
	fb_rawshader/mip_cube \
	fb_rawshader/stencil_test \
	fb_rawshader/rotate_cube \
	fb_rawshader/cubemap_sphere \
	fb_rawshader/cube_companion \
	fb_rawshader/displacement \
	fb_rawshader/alpha_blend \
	utils/viv_reset \
	utils/viv_throughput \
	utils/viv_gpu_top \
	utils/viv_info \
	utils/viv_registers \
	utils/viv_watch \
"

ETNAVIV_STATIC_LIBS = " \
	driver/libetnadriver.a \
	minigallium/libminigallium.a \
	etnaviv/libetnaviv.a \
"

ETNAVIV_HEADERS = " \
	etnaviv/etna_bo.h \
	etnaviv/state_2d.xml.h \
	etnaviv/viv.h \
	etnaviv/viv_internal.h \
	etnaviv/state_3d.xml.h \
	etnaviv/viv_profile.h \
	etnaviv/etna.h \
	etnaviv/etna_util.h \
	etnaviv/common.xml.h \
	etnaviv/state.xml.h \
	etnaviv/isa.xml.h \
	etnaviv/cmdstream.xml.h \
	etnaviv/etna_fb.h \
	etnaviv/state_hi.xml.h \
	etnaviv/etna_rs.h \
	etnaviv/etna_queue.h \
	etnaviv/etna_tex.h \
	etnaviv/state_vg.xml.h \
"

ETNAVIV_GC_HEADERS = " \
	include_${GCABI}/gc_hal.h \
	include_${GCABI}/gc_hal_engine_vg.h \
	include_${GCABI}/gc_hal_eglplatform.h \
	include_${GCABI}/gc_hal_rename.h \
	include_${GCABI}/gc_hal_kernel_buffer.h \
	include_${GCABI}/gc_hal_statistics.h \
	include_${GCABI}/gc_hal_driver.h \
	include_${GCABI}/gc_hal_driver_vg.h \
	include_${GCABI}/gc_hal_options.h \
	include_${GCABI}/gc_hal_dump.h \
	include_${GCABI}/gc_hal_engine.h \
	include_${GCABI}/gc_hal_eglplatform_type.h  \
	include_${GCABI}/aqHal.h \
	include_${GCABI}/gc_hal_enum.h \
	include_${GCABI}/gc_hal_types.h \
	include_${GCABI}/gc_hal_raster.h \
	include_${GCABI}/gc_hal_profiler.h \
	include_${GCABI}/gc_abi.h \
	include_${GCABI}/gc_hal_base.h \
	include_${GCABI}/gc_hal_vg.h \
	include_${GCABI}/gc_hal_version.h \
"

do_compile() {
	cd "${S}/src"
	oe_runmake
}

do_install() {
	LIBDESTDIR="${D}${libdir}"
	BINDESTDIR="${D}${bindir}"
	INCDESTDIR="${D}${includedir}"
	install -d "${LIBDESTDIR}"
	install -d "${BINDESTDIR}"
	install -d "${INCDESTDIR}"
	install -d "${INCDESTDIR}/etnaviv"
	install -d "${LIBDESTDIR}/resources"

	for executable in ${ETNAVIV_EXECUTABLES}
	do
		install -m 0755 "${S}/src/${executable}" "${BINDESTDIR}/"
	done

	for static_lib in ${ETNAVIV_STATIC_LIBS}
	do
		install -m 0644 "${S}/src/${static_lib}" "${LIBDESTDIR}/"
	done

	for header in ${ETNAVIV_HEADERS}
	do
		install -m 0644 "${S}/src/${header}" "${INCDESTDIR}/etnaviv/"
	done

	for header in ${ETNAVIV_GC_HEADERS}
	do
		install -m 0644 "${S}/src/${header}" "${INCDESTDIR}/"
	done

	install -m 0644 ${S}/src/resources/*.tga "${LIBDESTDIR}/resources"
	install -m 0644 ${S}/src/resources/*.dds "${LIBDESTDIR}/resources"
}

FILES_${PN} = " \
	${libdir}/resources/ \
	${bindir}/* \
"

FILES_${PN}-staticdev = " \
	${libdir} \
	${includedir} \
"

FILES_${PN}-dev = " \
	${includedir} \
"

FILES_${PN}-dbg += " \
	${bindir}/.debug \
	/usr/src/debug/ \
"
