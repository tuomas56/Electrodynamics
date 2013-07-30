mkdir build
cd build
../install/wget http://files.minecraftforge.net/minecraftforge/minecraftforge-src-1.6.2-9.10.0.804.zip
rename minecraftforge-src-1.6.2-9.10.0.804.zip forge.zip
mkdir forge
cd forge
../../install/7za x ../forge.zip
install
cd ..
del forge.zip
cd ..
gradle build
