#!/bin/zsh
echo 'aarch64'
JDK_INSTALLATION_DIR="/opt/jdk/"
TEMP_DIR='/USERS/'$USER'/Downloads'
VERSION=$(echo $1| cut -d'/' -f 8)
TAR=$(echo $1 | cut -d'/' -f 9)
DOWNLOAD_URL=$1
BASH='/bin/bash'
ZSH='/bin/zsh'
USED_RC_FILE=$HOME/.zshrc
JAVA_HOME_VAR='"'"$JDK_INSTALLATION_DIR$VERSION.jdk/Contents/Home"'"'
PATH_VAR='"'$JDK_INSTALLATION_DIR$VERSION'.jdk/bin:$PATH''"'
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

if [ -d "$JDK_INSTALLATION_DIR$VERSION.jdk" ]; then
 	echo "already installed"
 else
 	cd $TEMP_DIR && { curl -LJO  $DOWNLOAD_URL ; tar -xvzf $TAR; }
	rm $TEMP_DIR/$TAR
 	cp -R  $TEMP_DIR/$VERSION.jdk $JDK_INSTALLATION_DIR
 	rm -rf $TEMP_DIR/$VERSION.jdk	
fi

if echo "$JAVA_HOME" | grep "/opt/jdk/$VERSION.jdk/Contents/Home"; then
  echo "It's there! JAVA_HOME"
  else
	echo "It's not there! JAVA_HOME! But now it is."
	echo "export JAVA_HOME=$JAVA_HOME_VAR" >> $USED_RC_FILE
fi

echo $PATH | grep -q "/opt/jdk/$VERSION.jdk/bin:"
if [ $? -eq 0 ]; then
  echo  "It's there! PATH"
else
	echo "It's not there! PATH! But now it is."
  echo "export PATH=$PATH_VAR" >> $USED_RC_FILE
fi
 . $USED_RC_FILE
hdiutil attach "$DMG_PATH"
java -jar /Volumes/SAP\ GUI\ for\ Java\ Installer/SAP\ GUI\ for\ Java\ Installer.app/Contents/Resources/Java/PlatinGUI-MacOSXNoJRES.jar