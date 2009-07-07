#!/bin/bash

# This script runs as https://build.eclipse.org/hudson/job/cbi-*/configure
# and is archived in  http://dev.eclipse.org/viewcvs/index.cgi/org.eclipse.dash/athena/org.eclipse.dash.commonbuilder/org.eclipse.dash.commonbuilder.releng/hudson/?root=Technology_Project
# Build runs under ${WORKSPACE} == /opt/users/hudsonbuild/.hudson/jobs/cbi-*/workspace

echo "[`date +%Y/%m/%d\ %H:%M`] Hudson job ${JOBNAME} build #${BUILD_NUMBER} (${BUILD_ID}) started." 

##############################################################################################

# BEGIN CONFIGURATION

buildTimestamp="`date +%Y%m%d%H%M`"
projectid="ds.modeling.incubator"
version="0.1.0"

# leave blank to use assumed values (which may be wrong!)
projRelengRoot='-projRelengRoot :pserver:anonymous@dev.eclipse.org:/cvsroot/eclipse'
projRelengPath='-projRelengPath pde-incubator/modeling/releng/org.eclipse.pde.ds.releng

# DONE CONFIGURATION

##############################################################################################

# exposed as a Hudson build parameter for convenience
if [[ $BUILDTYPE ]]; then
	buildType="$BUILDTYPE"
else
	buildType="N"
fi

# pass in additional flags like -buildAlias=1.0.0RC2 using the $EXTRAFLAGS Hudson parameter
# buildAlias will rename zips from foo-SDK-N200901011234.zip to foo-SDK-1.0.0RC2.zip

##############################################################################################

# define where to do all the work; start with a fresh folder each time
writableBuildRoot="${WORKSPACE}/build"
if [[ -d ${writableBuildRoot} ]]; then rm -fr ${writableBuildRoot}; fi

# define required folders
downloadsDir="${writableBuildRoot}/downloads"
signingDir="${writableBuildRoot}/signing"

# long form (default if omitted)
# buildDir="${writableBuildRoot}/${projectid//.//}/downloads/drops/${version}/${buildType}${buildTimestamp}"
# short form (non-default)
buildDir="${writableBuildRoot}/${buildType}${buildTimestamp}"

# create required folders & files (as symlinks is possible)
mkdir -p ${downloadsDir} ${signingDir} ${buildDir}

# create .cvspass file to shut up unnecessary warnings
touch ${writableBuildRoot}/.cvspass

#define symlinked required folders
relengBaseBuilderDir="${writableBuildRoot}/org.eclipse.releng.basebuilder"
relengCommonBuilderDir="${writableBuildRoot}/org.eclipse.dash.common.releng"
# symlink basebuilder and common.releng; alternatively, if you omit this, they will be checked out in start.sh
ln -s /opt/public/cbi/build/org.eclipse.releng.basebuilder ${writableBuildRoot}/
ln -s /opt/public/cbi/build/org.eclipse.dash.common.releng ${writableBuildRoot}/

# symlink 3rdPartyJars (reuse existing content on build.eclipse.org for ant-contrib.jar, etc.)
thirdPartyJarsDir="${writableBuildRoot}/3rdPartyJars"
ln -s /opt/public/cbi/build/3rdPartyJars ${writableBuildRoot}/

# run a build
cd ${writableBuildRoot}/org.eclipse.dash.common.releng/tools/scripts
./start.sh -projectid ${projectid} -version ${version} -buildType ${buildType} -buildTimestamp ${buildTimestamp} \
  -writableBuildRoot ${writableBuildRoot} -thirdPartyJarsDir ${thirdPartyJarsDir} -downloadsDir ${downloadsDir} -buildDir ${buildDir} \
  ${projRelengRoot} ${projRelengPath} ${EXTRAFLAGS} 2>&1

# remove file so workspace navigation is one click simpler
rm -f ${writableBuildRoot}/.cvspass

echo "[`date +%Y/%m/%d\ %H:%M`] Hudson job ${JOBNAME} build #${BUILD_NUMBER} (${BUILD_ID}) done." 
