//
//  DetailViewController.m
//  Todolist
//
//  Created by Marc DIJOUX on 21/05/2014.
//  Copyright (c) 2014 Marc Dijoux. All rights reserved.
//

#import "DetailViewController.h"
#import "Task.h"

@interface DetailViewController ()
- (IBAction)onSave:(id)sender;
- (IBAction)onCancel:(id)sender;
- (void)configureView;
@end

@implementation DetailViewController

@synthesize itemsArray;

#pragma mark - Managing the detail item

- (void)setDetailItem:(id)newDetailItem
{
    if (_detailItem != newDetailItem) {
        _detailItem = newDetailItem;
        
        // Update the view.
        [self configureView];
    }
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self.view endEditing:YES];
    return NO;
}

- (IBAction)onSave:(id)sender {
    if (self.detailItem) {
        Task* t = ((Task*)self.detailItem);
        [t setName:self.nameTextField.text];
        [t setPriority:[NSNumber numberWithInt:(int)self.priorityPicker.value]];
        [t setCategory:[itemsArray objectAtIndex:[self.categoryPicker selectedRowInComponent:0]]];
    }
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)onCancel:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}


- (void)configureView
{
    // Update the user interface for the detail item.

    if (self.detailItem) {
        Task* t = ((Task*)self.detailItem);
        self.nameTextField.text = t.name;
        self.priorityPicker.value = [t.priority floatValue];
        self.priorityLabel.text = [NSString stringWithFormat:@"Priority: %d", [t.priority intValue]];
        [self.categoryPicker selectRow:[itemsArray indexOfObject:t.category] inComponent:0 animated:NO];
    }
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    itemsArray = [[NSArray alloc] initWithObjects:@"Default",
                        @"Professional", @"Personnal", @"Divers", nil];
    
	// Do any additional setup after loading the view, typically from a nib.
    [self configureView];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)priorityValueChanged:(id)sender {
    int priority = (int)self.priorityPicker.value;
    self.priorityPicker.value = (float)priority;
    self.priorityLabel.text = [NSString stringWithFormat:@"Priority: %d", priority];
}


#pragma mark - UIPickerView DataSource
// returns the number of 'columns' to display.
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 1;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    return [itemsArray count];
}

#pragma mark - UIPickerView Delegate
- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component
{
    return [itemsArray objectAtIndex:row];
}

@end
