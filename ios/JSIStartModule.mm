#import "JSIStartModule.h"

#import <React/RCTBridge+Private.h>
#import <React/RCTUtils.h>
#import <ReactCommon/RCTTurboModule.h>
#import <jsi/jsi.h>

#import "JSIStartHostObject.h"

@implementation JSIStartModule

@synthesize bridge = _bridge;
@synthesize methodQueue = _methodQueue;

RCT_EXPORT_MODULE(JSIStart)

+ (BOOL)requiresMainQueueSetup {

    return YES;
}

- (void)setBridge:(RCTBridge *)bridge {
    _bridge = bridge;
    _setBridgeOnMainQueue = RCTIsMainQueue();
}



RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(install)
{
  NSLog(@"Installing JSI bindings for react-native-jsi-start...");
  RCTCxxBridge* cxxBridge = (RCTCxxBridge*) self.bridge;
    if (cxxBridge == nil) {
        return @false;
    }
    
    // Totp JSI Binding
    margelo::install(*(facebook::jsi::Runtime *)cxxBridge.runtime);
    
    NSLog(@"Installed JSIStart JSI Bindings!");
    return @true;
}

@end
