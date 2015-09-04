require 'calabash-android/calabash_steps'

# When(/^I enter "(.*?)"$/) do |arg1|
#   enter_text("android.widget.EditText", arg1)
# end

Then /^I scroll to cell with "([^\"]*)" label and touch it$/ do |name|
  element="TextView text:'#{name}'"      
  if !element_exists(element)
    wait_poll(timeout: 10,
          timeout_message: 'Unable to find "Example"', :until_exists => "TextView text:'#{name}'") do
        scroll_down
    end
    if element_exists(element)
        touch element 
        touch element 
        # sleep(10)
    else
        screenshot_and_raise "could not find the cell"
    end
    else
        touch element
        touch element
        # sleep(10)
    end    
end

Then(/^I wait and wait$/) do
  wait_for_element_does_not_exist("android.widget.ProgressBar")
end


Then(/^I select tab number "(.*?)"$/) do |tab|
	touch("android.view.View descendant TextView index:#{tab.to_i}")
end
