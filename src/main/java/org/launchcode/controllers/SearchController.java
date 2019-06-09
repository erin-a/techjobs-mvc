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

    // TODO #1 - Create handler to process search request and display results
    // The method should take in two parameters,
    // specifying the type of search and the search term.

    // need to name them appropriately, based on the corresponding form field names.

    // need to use the correct annotations for the method and parameters. To configure
    // the correct mapping route, refer to the form action in search.html.

    //you'll need to pass them into the search.html view via the model.

    //need to pass ListController.columnChoices to the view, as the existing search handler does.

}
