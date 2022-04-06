#!/bin/zsh
echo 'aarch64'
#param1 = DownloadURL
#param2 = Path to .Dmg file
VERSION=$(echo $1| cut -d'/' -f 8)
TAR=$(echo $1 | cut -d'/' -f 9)
SAPMACHINE=$(echo $VERSION | cut -d'-' -f 1)
JDK_INSTALLATION_DIR="/opt/jdk/"
TEMP_DIR='/Users/'$USER'/Downloads'
VERSION_NR=$(echo $VERSION | cut -d'-' -f 2)
DOWNLOAD_URL=$1
BASH='/bin/bash'
ZSH='/bin/zsh'
USED_RC_FILE=$HOME/.zshrc
JAVA_HOME_VAR='"'"$JDK_INSTALLATION_DIR$SAPMACHINE-jdk-$VERSION_NR.jdk/Contents/Home"'"'
PATH_VAR='"'$JDK_INSTALLATION_DIR$SAPMACHINE-jdk-$VERSION_NR.jdk'.jdk/bin:$PATH''"'
DMG_PATH=$2

[ -z "$SHELL" ] && SHELL=$ZSH
if env | grep -q ^SHELL=$ZSH
then
	echo "ZSH is default"
  	USED_RC_FILE=$HOME/.zshrc
else
	echo "Bash is default"
 	USED_RC_FILE=$HOME/.bashrc
fi		

if [ -d "$JDK_INSTALLATION_DIR$SAPMACHINE-jdk-$VERSION_NR.jdk" ]; then
 	echo "already installed"
else
	if [ -d "$TEMP_DIR/$SAPMACHINE-jdk-$VERSION_NR.jdk" ]; then
		echo "already downloaded"
		cp -R  $TEMP_DIR/$SAPMACHINE-jdk-$VERSION_NR.jdk $JDK_INSTALLATION_DIR
 		rm -rf $TEMP_DIR/$SAPMACHINE-jdk-$VERSION_NR.jdk
	else
		cd $TEMP_DIR && { curl -LJO  $DOWNLOAD_URL ; tar -xvzf $TAR; }
		rm $TEMP_DIR/$TAR
 		cp -R  $TEMP_DIR/$SAPMACHINE-jdk-$VERSION_NR.jdk $JDK_INSTALLATION_DIR
 		rm -rf $TEMP_DIR/$SAPMACHINE-jdk-$VERSION_NR.jdk
	fi
fi

if 	echo "$JAVA_HOME" | grep "/opt/jdk/$SAPMACHINE-jdk-$VERSION_NR.jdk/Contents/Home"; then
  	echo "It's there! JAVA_HOME"
else
	echo "It's not there! JAVA_HOME! But now it is."
	echo "export JAVA_HOME=$JAVA_HOME_VAR" >> $USED_RC_FILE
fi

echo $PATH | grep -q "/opt/jdk/$SAPMACHINE-jdk-$VERSION_NR.jdk/bin:"
if [ $? -eq 0 ]; then
  	echo  "It's there! PATH"
else
	echo "It's not there! PATH! But now it is."
	echo "export PATH=$PATH_VAR" >> $USED_RC_FILE
fi
 . $USED_RC_FILE
hdiutil attach "$DMG_PATH"
java -jar /Volumes/SAP\ GUI\ for\ Java\ Installer/SAP\ GUI\ for\ Java\ Installer.app/Contents/Resources/Java/PlatinGUI-MacOSXNoJRES.jar