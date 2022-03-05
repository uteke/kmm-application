import SwiftUI
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        let component: IosProviderComponent = IosProviderComponent()

		WindowGroup {
            let productListViewModel = ProductListObservableViewModel(
                viewModel: component.provideProductListViewModel()
            )
            
            ProductListScreen(viewModel: productListViewModel)
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        ModuleInjectionsKt.doInitDependencies()
        return true
    }
}
