# Install script for directory: /Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Install shared libraries without execute permission?
if(NOT DEFINED CMAKE_INSTALL_SO_NO_EXE)
  set(CMAKE_INSTALL_SO_NO_EXE "0")
endif()

# Is this installation the result of a crosscompile?
if(NOT DEFINED CMAKE_CROSSCOMPILING)
  set(CMAKE_CROSSCOMPILING "TRUE")
endif()

if(NOT CMAKE_INSTALL_LOCAL_ONLY)
  # Include the install script for each subdirectory.
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/core/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/verifier/eth1/nano/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/verifier/eth1/evm/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/verifier/eth1/basic/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/verifier/eth1/full/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/java/src/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/third-party/crypto/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/third-party/tommath/outputs/cmake_install.cmake")
  include("/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/in3-c/c/src/api/outputs/cmake_install.cmake")

endif()

if(CMAKE_INSTALL_COMPONENT)
  set(CMAKE_INSTALL_MANIFEST "install_manifest_${CMAKE_INSTALL_COMPONENT}.txt")
else()
  set(CMAKE_INSTALL_MANIFEST "install_manifest.txt")
endif()

string(REPLACE ";" "\n" CMAKE_INSTALL_MANIFEST_CONTENT
       "${CMAKE_INSTALL_MANIFEST_FILES}")
file(WRITE "/Users/belga/Documents/Trabajo/Bitnovo/Bitnovo3/ethereum-kit-android/ethereumkit/.cxx/cmake/debug/x86/${CMAKE_INSTALL_MANIFEST}"
     "${CMAKE_INSTALL_MANIFEST_CONTENT}")
