package am.itspace.smart_education.common.services;

import am.itspace.smart_education.common.entity.Subscription;
import am.itspace.smart_education.common.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public void save(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public void deleteById(int id) {
        subscriptionRepository.deleteById(id);
    }


}
