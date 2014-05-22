//
//  DetailViewController.h
//  Todolist
//
//  Created by Marc DIJOUX on 21/05/2014.
//  Copyright (c) 2014 Marc Dijoux. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController <UIPickerViewDataSource, UIPickerViewDelegate>
@property (weak, nonatomic) IBOutlet UITextField *nameTextField;
@property (weak, nonatomic) IBOutlet UILabel *priorityLabel;
@property (weak, nonatomic) IBOutlet UISlider *priorityPicker;
@property (weak, nonatomic) IBOutlet UIPickerView *categoryPicker;
- (IBAction)priorityValueChanged:(id)sender;

@property (strong, nonatomic) id detailItem;
@property (nonatomic, strong) NSArray *itemsArray;

@end
