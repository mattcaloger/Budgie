package com.caloger.Budgie.ScheduledService;

import com.caloger.Budgie.Categories.Category;
import com.caloger.Budgie.Categories.CategoryService;
import com.caloger.Budgie.Transactions.Transaction;
import com.caloger.Budgie.Transactions.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class ScheduledService {

    CategoryService categoryService;

    TransactionService transactionService;

    public ScheduledService(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    // scheduled every 6 hours
    @Scheduled(cron="0 0 */6 * * *")
    public Transaction CreateExampleTransaction() throws Exception {

        // get random category
        List<Category> categories = categoryService.getAllCategories();

        Random random = new Random();
        Category randomCategory = categories.get(random.nextInt(categories.size()));

        int plusOrMinus = random.nextInt(2);

        // get random amount
        BigDecimal maximum = new BigDecimal(250);
        BigDecimal randomBigDecimalFromDouble = new BigDecimal(Math.random());
        BigDecimal randomDecimal = randomBigDecimalFromDouble.multiply(maximum);
        randomDecimal = randomDecimal.setScale(2, RoundingMode.DOWN);

        if(plusOrMinus == 1) {
            randomDecimal = randomDecimal.negate();
        }

        // generate when this expense will occur
        LocalDate localDate = LocalDate.now();
        int timeSelector = random.nextInt(5);
        int jumpSelector = random.nextInt(5);

        // if 0-3, add/remove time, else, stay where it is
        switch (timeSelector) {
            case 0:
                localDate = localDate.plusDays(jumpSelector);
                break;
            case 1:
                localDate = localDate.plusWeeks(jumpSelector);
                break;
            case 2:
                localDate = localDate.minusWeeks(jumpSelector);
                break;
            case 3:
                localDate = localDate.minusDays(jumpSelector);
                break;
        }

        return transactionService.saveIncome(new Transaction(randomDecimal, randomCategory, "Generated example",
                localDate));
    }
}
