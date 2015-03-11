package com.jandar.polytech.customerservice.view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jandar.polytech.customerservice.R;

public class MyDialog extends Dialog {
 
    public MyDialog(Context context, int theme) {
        super(context, theme);
    }
 
    
    public MyDialog(Context context) {
        super(context);
    }
 
    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
 
        private Context context;
        private String title;
        private CharSequence message;
        private boolean closeButton = false;
        private String positiveButtonText;
        private String negativeButtonText;
        private String phoneButtonText;
 
        private DialogInterface.OnClickListener 
                        positiveButtonClickListener,
                        negativeButtonClickListener,
                        phoneButtonClickListener;
 
        public Builder(Context context) {
            this.context = context;
        }
 
        /**
         * Set the Dialog message from String
         * @param title
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        
        public Builder setMessage(CharSequence message){
        	this.message = message;
        	return this;
        }
 
        /**
         * Set the Dialog message from resource
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }
 
        /**
         * Set the Dialog title from resource
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }
 
        /**
         * Set the Dialog title from String
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        
        public Builder setCloseButton(boolean closeButton){
        	this.closeButton = closeButton;
        	return this;
        }
        /**
         * Set the positive button resource and it's listener
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        /**
         * Set the positive button text and it's listener
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        /**
         * Set the negative button resource and it's listener
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        /**
         * Set the negative button text and it's listener
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }
        
        public Builder setPhoneButton(int phoneButtonText,
                DialogInterface.OnClickListener listener) {
            this.phoneButtonText = (String) context
                    .getText(phoneButtonText);
            this.phoneButtonClickListener = listener;
            return this;
        }
 
        public Builder setPhoneButton(String phoneButtonText,
                DialogInterface.OnClickListener listener) {
            this.phoneButtonText = phoneButtonText;
            this.phoneButtonClickListener = listener;
            return this;
        }
        
        /**
         * Create the custom dialog
         */
        public MyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MyDialog dialog = new MyDialog(context,R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_common, null);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.addContentView(layout, new LayoutParams(50, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.dtitle)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(
                                    		dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                	negativeButtonClickListener.onClick(
                                    		dialog, 
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            
            if (phoneButtonText != null) {
                ((TextView) layout.findViewById(R.id.phoneButton))
                        .setText(phoneButtonText);
                if (phoneButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.phoneButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                	phoneButtonClickListener.onClick(
                                    		dialog, 
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.phoneButton).setVisibility(
                        View.GONE);
            }
            
            if(closeButton){
            	layout.findViewById(R.id.closeDialog).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						DialogInterface.OnClickListener listener = new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						};
						listener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
					}
				});
            }else{
            	layout.findViewById(R.id.closeDialog).setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(
                		R.id.message)).setText(message);
            } else{
            	((LinearLayout) layout.findViewById(
                		R.id.message)).setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            return dialog;
        }
 
    }
 
}