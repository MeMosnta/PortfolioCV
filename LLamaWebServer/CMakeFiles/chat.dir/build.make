# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.26

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CMake.app/Contents/bin/cmake

# The command to remove a file.
RM = /Applications/CMake.app/Contents/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/Users/lcanham/alpaca copy.cpp"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/lcanham/alpaca copy.cpp"

# Include any dependencies generated for this target.
include CMakeFiles/chat.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/chat.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/chat.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/chat.dir/flags.make

CMakeFiles/chat.dir/chat.cpp.o: CMakeFiles/chat.dir/flags.make
CMakeFiles/chat.dir/chat.cpp.o: chat.cpp
CMakeFiles/chat.dir/chat.cpp.o: CMakeFiles/chat.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/lcanham/alpaca copy.cpp/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/chat.dir/chat.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/chat.dir/chat.cpp.o -MF CMakeFiles/chat.dir/chat.cpp.o.d -o CMakeFiles/chat.dir/chat.cpp.o -c "/Users/lcanham/alpaca copy.cpp/chat.cpp"

CMakeFiles/chat.dir/chat.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/chat.dir/chat.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/lcanham/alpaca copy.cpp/chat.cpp" > CMakeFiles/chat.dir/chat.cpp.i

CMakeFiles/chat.dir/chat.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/chat.dir/chat.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/lcanham/alpaca copy.cpp/chat.cpp" -o CMakeFiles/chat.dir/chat.cpp.s

CMakeFiles/chat.dir/utils.cpp.o: CMakeFiles/chat.dir/flags.make
CMakeFiles/chat.dir/utils.cpp.o: utils.cpp
CMakeFiles/chat.dir/utils.cpp.o: CMakeFiles/chat.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/Users/lcanham/alpaca copy.cpp/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/chat.dir/utils.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/chat.dir/utils.cpp.o -MF CMakeFiles/chat.dir/utils.cpp.o.d -o CMakeFiles/chat.dir/utils.cpp.o -c "/Users/lcanham/alpaca copy.cpp/utils.cpp"

CMakeFiles/chat.dir/utils.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/chat.dir/utils.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/lcanham/alpaca copy.cpp/utils.cpp" > CMakeFiles/chat.dir/utils.cpp.i

CMakeFiles/chat.dir/utils.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/chat.dir/utils.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/lcanham/alpaca copy.cpp/utils.cpp" -o CMakeFiles/chat.dir/utils.cpp.s

# Object files for target chat
chat_OBJECTS = \
"CMakeFiles/chat.dir/chat.cpp.o" \
"CMakeFiles/chat.dir/utils.cpp.o"

# External object files for target chat
chat_EXTERNAL_OBJECTS =

chat: CMakeFiles/chat.dir/chat.cpp.o
chat: CMakeFiles/chat.dir/utils.cpp.o
chat: CMakeFiles/chat.dir/build.make
chat: libggml.a
chat: CMakeFiles/chat.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/Users/lcanham/alpaca copy.cpp/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable chat"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/chat.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/chat.dir/build: chat
.PHONY : CMakeFiles/chat.dir/build

CMakeFiles/chat.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/chat.dir/cmake_clean.cmake
.PHONY : CMakeFiles/chat.dir/clean

CMakeFiles/chat.dir/depend:
	cd "/Users/lcanham/alpaca copy.cpp" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/lcanham/alpaca copy.cpp" "/Users/lcanham/alpaca copy.cpp" "/Users/lcanham/alpaca copy.cpp" "/Users/lcanham/alpaca copy.cpp" "/Users/lcanham/alpaca copy.cpp/CMakeFiles/chat.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/chat.dir/depend

