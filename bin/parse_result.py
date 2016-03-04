import sys
import os
import subprocess

__author__ = 'Anson Liao'


history_dir = "TestHistory"

# Argument No. check
if len(sys.argv) <= 1:
	print "ERROR!! No argument found."
	print "Please provide build number of Jenkins Job."
	print "     Useage:"
	print "             python parse_result.py [Jenkins Build Number]"
	sys.exit(1)
else:
	build_number = sys.argv[1]
	if not str(build_number).isdigit():
		print "ERROR!! argument: [%s] is not integer value."%build_number
		sys.exit(1)


dest_history_dir = history_dir + "/" + build_number
dest_screenshot_dir = dest_history_dir + "/target/surefire-reports/Screenshots"

# Create test history folder
if not os.path.exists(history_dir):
    cmd_str = ['mkdir', history_dir]
    (subprocess.Popen(cmd_str)).wait()

# Create build number folder
cmd_str = ['mkdir', dest_history_dir]
(subprocess.Popen(cmd_str)).wait()

# Copy test result to test history folder
cmd_str = ['cp', '-r', 'target', dest_history_dir]
(subprocess.Popen(cmd_str)).wait()

# Parse result
if os.path.exists(dest_screenshot_dir):
	print "====> Screenshot folder found."
	if len(os.listdir(dest_screenshot_dir)) == 0:
		print "====> No screenshot image found."
		print "====> TEST PASS."
		sys.exit(0)
	elif len(os.listdir(dest_screenshot_dir)) == 1:
		if os.listdir(dest_screenshot_dir)[0] == '.DS_Store':
			print "====> No screenshot image found."
			print "====> TEST PASS."
			sys.exit(0)
        else:
            print "====> Screenshot image found."
            print "====> Some test cases fail."
            print "====> TEST FAIL."
            sys.exit(1)
else:
    print "====> Screenshot folder not found."
    print "====> TEST PASS."
    sys.exit(0)