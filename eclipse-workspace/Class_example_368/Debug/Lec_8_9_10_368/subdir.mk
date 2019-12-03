################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Lec_8_9_10_368/Lecture_10.cpp \
../Lec_8_9_10_368/Lecture_8.cpp \
../Lec_8_9_10_368/Lecture_9.cpp \
../Lec_8_9_10_368/POINT.cpp 

OBJS += \
./Lec_8_9_10_368/Lecture_10.o \
./Lec_8_9_10_368/Lecture_8.o \
./Lec_8_9_10_368/Lecture_9.o \
./Lec_8_9_10_368/POINT.o 

CPP_DEPS += \
./Lec_8_9_10_368/Lecture_10.d \
./Lec_8_9_10_368/Lecture_8.d \
./Lec_8_9_10_368/Lecture_9.d \
./Lec_8_9_10_368/POINT.d 


# Each subdirectory must supply rules for building sources it contributes
Lec_8_9_10_368/%.o: ../Lec_8_9_10_368/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


