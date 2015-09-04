Feature: Zomato Test Flow

  Scenario: I should be able to see the final restaurant
    # When I press "Send"
    # Then I see ""
		Then I scroll to cell with "Goa" label and touch it
		Then I see "Chinese"
		Then I scroll to cell with "Chinese" label and touch it
		Then I see "Tao"
		Then I scroll to cell with "Tao" label and touch it
		Then I see "Tao"
		Then I wait and wait
		Then I wait
		Then I go back
		Then I go back
		Then I wait
		Then I select tab number "1"
		Then I scroll to cell with "Bogmalo" label and touch it
		Then I see "Full Moon"
		Then I scroll to cell with "Full Moon" label and touch it
		Then I see "Full Moon"
		Then I wait and wait
		Then I wait
		Then I go back

