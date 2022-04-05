INPUT='https://github.com/SAP/SapMachine/releases/download/sapmachine-17.0.2/sapmachine-jdk-17.0.2_macos-aarch64_bin.tar.gz'
TAR=$(echo $INPUT | cut -d'_' -f 2)
echo $TAR