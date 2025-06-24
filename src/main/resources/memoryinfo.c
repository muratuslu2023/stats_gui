#include <stdio.h>
#include <mach/mach.h>
#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_murat_1uslu_utils_MemoryInfo_getMemoryInfo(JNIEnv *env, jobject obj);

JNIEXPORT jstring JNICALL Java_com_murat_1uslu_utils_MemoryInfo_getMemoryInfo(JNIEnv *env, jobject obj) {

    char buffer[256];
    mach_msg_type_number_t count = HOST_VM_INFO64_COUNT;
    vm_statistics64_data_t vmstat;

    kern_return_t kr;

    kr = host_statistics64(mach_host_self(), HOST_VM_INFO64, (host_info64_t)&vmstat, &count);

    if(kr != KERN_SUCCESS){
        printf("Failed to get VM statistics!\n");
        return (*env)->NewStringUTF(env, "");
    }

    int64_t page_size;

    host_page_size(mach_host_self(), (vm_size_t*)&page_size);

    uint64_t free_memory = (int64_t)vmstat.free_count * page_size;
    uint64_t active_memory = (int64_t)vmstat.active_count * page_size;
    uint64_t inactive_memory = (int64_t)vmstat.inactive_count * page_size;
    uint64_t wired_memory = (int64_t)vmstat.wire_count * page_size;


    snprintf(buffer, sizeof(buffer),
        "Free: %.2f MB\nActive: %.2f MB\nInactive: %.2f MB\nWired: %.2f MB\n",
        free_memory / 1024.0 / 1024.0,
        active_memory / 1024.0 / 1024.0,
        inactive_memory / 1024.0 / 1024.0,
        wired_memory / 1024.0 / 1024.0
    );

    return (*env)->NewStringUTF(env, buffer);
}