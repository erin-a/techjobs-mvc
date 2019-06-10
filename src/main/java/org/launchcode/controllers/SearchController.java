package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")  // would it be: value = "results/{searchType}/{searchName}"  ??
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        ArrayList<HashMap<String, String>> searchresults;
        //if searching for all do search A
        if (searchType.equals("all")) {
            searchresults = JobData.findByValue(searchTerm);
        }
        // else do search B
        else {
            searchresults = JobData.findByColumnAndValue(searchType, searchTerm);

        }



        model.addAttribute("columns", ListController.columnChoices);
        //model.addAttribute("", ListController.???)
        model.addAttribute("searchresults", searchresults);

        return "search";
    }


    // TODO #1 - Create handler to process search request and display results
    // The method should take in two parameters,
    // specifying the type of search and the search term.

    // need to name them appropriately, based on the corresponding form field names.

    // need to use the correct annotations for the method and parameters. To configure
    // the correct mapping route, refer to the form action in search.html. (should be results)

    //you'll need to pass them into the search.html view via the model. (addAttribute)

    //need to pass ListController.columnChoices to the view, as the existing search handler does.

}
