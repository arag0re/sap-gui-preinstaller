echo 'aarch64'
JDK_INSTALLATION_DIR="/opt/jdk/"
TEMP_DIR='/USERS/'$USER'/Downloads'
VERSION='sapmachine-jdk-17.0.2'
DOWNLOAD_URL='https://github.com/SAP/SapMachine/releases/download/sapmachine-17.0.2/sapmachine-jdk-17.0.2_macos-aarch64_bin.tar.gz'

if [ -d "$JDK_INSTALLATION_DIR$VERSION.jdk" ]; then
 	 	 echo "already installed"
 else
 	cd $TEMP_DIR && { curl -LJO  $DOWNLOAD_URL ; tar -xvzf $VERSION"_macos-aarch64_bin.tar.gz"; }
	rm $TEMP_DIR/$VERSION"_macos-aarch64_bin.tar.gz"
 	cp -R  $TEMP_DIR/$VERSION.jdk $JDK_INSTALLATION_DIR
 	rm -rf $TEMP_DIR/$VERSION.jdk   
	echo export PATH='"'$JDK_INSTALLATION_DIR$VERSION'.jdk/bin:$PATH"'>> ~/.zshrc	
	echo export JAVA_HOME=$JDK_INSTALLATION_DIR$VERSION.jdk/Contents/Home >> ~/.zshrc
fi
#check if thes variables are in zsh
#check for shell