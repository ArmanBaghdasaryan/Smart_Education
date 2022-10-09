//package am.itspace.smart_education.controller.admin;
//
//import am.itspace.smart_education.common.entity.Subscription;
//import am.itspace.smart_education.common.services.SubscriptionService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class AdminSubscriptionController {
//
//    private final SubscriptionService subscriptionService;
//
//    @GetMapping("/admin/subscription")
//    public String subscriptionHome() {
//        return "subscription";
//    }
//
//
//    @GetMapping("/admin/subscription")
//    public String subscription(ModelMap modelMap) {
//        List<Subscription> allSubscription = subscriptionService.findAll();
//        modelMap.addAttribute("subscription", allSubscription);
//        return "subscription";
//    }
//
//    @GetMapping("/admin/subscription/add")
//    public String addSubscription() {
//        return "addSubscription";
//    }
//
//
//    @PostMapping("/admin/subscription/add")
//    public String addSubscription(@ModelAttribute Subscription subscription) {
//        subscriptionService.save(subscription);
//        return "redirect:/admin/subscription";
//    }
//
//
//    @GetMapping("/admin/subscription/delete")
//    public String deleteSubscription(@RequestParam("id") int id) {
//        subscriptionService.deleteById(id);
//        return "redirect:/admin/subscription";
//    }
//
//    @GetMapping("/admin/subscription/update")
//    public String update(@ModelAttribute Subscription subscription) {
//        subscriptionService.save(subscription);
//        return "redirect:/admin/subscription";
//    }
//
//}
