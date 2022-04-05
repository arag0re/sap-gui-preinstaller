#!/bin/zsh
echo 'aarch64'
JDK_INSTALLATION_DIR="/opt/jdk/"
TEMP_DIR='/USERS/'$USER'/Downloads'
VERSION='sapmachine-jdk-17.0.2'
DOWNLOAD_URL='https://github.com/SAP/SapMachine/releases/download/sapmachine-17.0.2/sapmachine-jdk-17.0.2_macos-aarch64_bin.tar.gz'
INSTALLER_DIR=''
BASH='/bin/bash'
ZSH='/bin/zsh'
USED_RC_FILE=$HOME/.zshrc
JAVA_HOME_VAR='"'"$JDK_INSTALLATION_DIR$VERSION.jdk/Contents/Home"'"'
PATH_VAR='"'$JDK_INSTALLATION_DIR$VERSION'.jdk/bin:$PATH''"'

[ -z "$SHELL" ] && SHELL=$ZSH
if env | grep -q ^SHELL=$ZSH
	then
	    echo "ZSH is default"
  		USED_RC_FILE=$HOME/.zshrc
	else
	    echo "Bash is default"
 		USED_RC_FILE=$HOME/.bashrc
fi		

if [ -d "$JDK_INSTALLATION_DIR$VERSION.jdk" ]; then
 	echo "already installed"
 else
 	cd $TEMP_DIR && { curl -LJO  $DOWNLOAD_URL ; tar -xvzf $VERSION"_macos-aarch64_bin.tar.gz"; }
	rm $TEMP_DIR/$VERSION"_macos-aarch64_bin.tar.gz"
 	cp -R  $TEMP_DIR/$VERSION.jdk $JDK_INSTALLATION_DIR
 	rm -rf $TEMP_DIR/$VERSION.jdk	
fi

if echo "$JAVA_HOME" | grep "/opt/jdk/sapmachine-jdk-17.0.2.jdk/Contents/Home"; then
  echo "It's there! JAVA_HOME"
  else
	echo "It's not there! JAVA_HOME! But now it is."
	echo "export JAVA_HOME=$JAVA_HOME_VAR" >> $USED_RC_FILE
fi

echo $PATH | grep -q "/opt/jdk/sapmachine-jdk-17.0.2.jdk/bin:"
if [ $? -eq 0 ]; then
  echo  "It's there! PATH"
else
	echo "It's not there! PATH! But now it is."
  echo "export PATH=$PATH_VAR" >> $USED_RC_FILE
fi
 . $USED_RC_FILE
hdiutil attach /Users/antonstadie/Documents/SAP/SAP\ GUI/SAPGUI_7.70_PL5-macOS-GUI770Installation_5-70004682.dmg
java -jar /Volumes/SAP\ GUI\ for\ Java\ Installer/SAP\ GUI\ for\ Java\ Installer.app/Contents/Resources/Java/PlatinGUI-MacOSXNoJRES.jar