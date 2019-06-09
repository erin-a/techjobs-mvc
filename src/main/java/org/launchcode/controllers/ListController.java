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
 *
 * This controller provides functionality for users to see lists of all values
 * of a given data column: employer, location, skill, and position type.
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    /**
     * [This] is a constructor that we use to populate columnChoices with values.
     * The columnChoices HashMap plays the same role as it did in the console app,
     * which is to provide a centralized collection of the different list and
     * search options presented throughout the user interface.
     */

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }


    /**
     * [the 'list' handler method]  displays the different types of lists that the
     * user can view. The latter two display actual data obtained from JobData.
     */
    @RequestMapping(value = "")
    public String list(Model model) {

        model.addAttribute("columns", columnChoices);

        return "list";
    }

    /**
     * [the 'listColumnValues' handler method] the controller uses the query
     * parameter passed in as column to determine which values to fetch
     * from JobData.
     *
     *
     * In the case of "all" it will fetch all job data, and then render the
     * list-jobs.html view template. In all other cases, it fetches only the
     * values for the given column and passes them to the list-column.html
     * view template
     */

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }

    /**
     *[the 'listJobsByColumnAndValue' handler method] we take in two query
     *  parameters: column and value. This has the net result of working
     *  similarly to the search functionality, in that we are "searching"
     *  for a particular value within a particular column and then displaying
     *  jobs that match. However, this is slightly different from the other
     *  way of searching in that the user will arrive at this handler method
     *  as a result of clicking on a link within one of our views, rather than
     *  via submitting a form. We'll see where these links originate when we
     *  look at the views.
     */

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}
