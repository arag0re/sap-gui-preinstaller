
echo 'aarch64'
JDK_INSTALLATION_DIR="/opt/jdk/"
TEMP_DIR='/Users/'$USER'/Downloads'
VERSION='sapmachine-jdk-17.0.2'
DOWNLOAD_URL='https://github.com/SAP/SapMachine/releases/download/sapmachine-17.0.2/sapmachine-jdk-17.0.2_macos-aarch64_bin.tar.gz'
INSTALLER_DIR=''
BASH='/bin/bash'
ZSH='/bin/zsh'
IMAGE_PATH=/Users/antonstadie/Documents/SAP/SAP\ GUI/SAPGUI_7.70_PL5-macOS-GUI770Installation_5-70004682.dmg
USED_RC_FILE=""
JAVA_HOME_VAR='"'"$JDK_INSTALLATION_DIR$VERSION.jdk/Contents/Home"'"'
PATH_VAR='"'$JDK_INSTALLATION_DIR$VERSION'.jdk/bin:$PATH''"'

[ -z "$SHELL" ] && SHELL=$ZSH
if env | grep -q ^SHELL=$ZSH
	then
  		USED_RC_FILE=$HOME/.zshrc
	else
 		USED_RC_FILE=$HOME/.bashrc
fi	

#[ -z "$JAVA_HOME" ] && JAVA_HOME=$JAVA_HOME_VAR
#if env | grep -q ^JAVA_HOME=
#then
#  echo env variable is already exported
#else
#  echo "export JAVA_HOME=$JAVA_HOME_VAR" >> $USED_RC_FILE
#fi;
#if [ "$JAVA_HOME" == "/opt/jdk/sapmachine-jdk-17.0.2.jdk/Contents/Home" ];then
 # echo match
  #else 
  #echo notmatch
  #echo "export JAVA_HOME=$JAVA_HOME_VAR" >> $USED_RC_FILE
#fi

#if [ "$PATH" = "/opt/jdk/sapmachine-jdk-17.0.2.jdk/bin:$PATH" ]; then
 # echo "It's there! PATH"
#  else
#	echo "It's not there! PATH"
#	echo "\n export PATH=$PATH_VAR" >> $USED_RC_FILE
#fi



echo $PATH | grep -q "/opt/jdk/sapmachine-jdk-17.0.2.jdk/bin:"

if [ $? -eq 0 ]; then
  echo no
else
  echo "\n export PATH=$PATH_VAR" >> $USED_RC_FILE
fi
