echo 'x86_64'
DMG_PATH=$2
hdiutil attach "$DMG_PATH"
open /Volumes/SAP\ GUI\ for\ Java\ Installer/SAP\ GUI\ for\ Java\ Installer.app

