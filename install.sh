mkdir build
cd build
wget http://files.minecraftforge.net/minecraftforge/minecraftforge-src-1.6.2-9.10.0.804.zip
mv minecraftforge-src-1.6.2-9.10.0.804.zip forge.zip
mkdir forge
cd forge
gunzip ../forge.zip
sh install.sh
cd ..
rm forge.zip
cd ..
gradle build